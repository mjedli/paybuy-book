import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../customer/service.component';
import { InvoiceService } from '../service.component';
import { Customer } from '../../customer/model/customer';
import { Invoice } from '../model/invoice';
import { SearchInvoice } from '../model/SearchInvoice';

@Component({
  selector: 'app-searchinvoice',
  templateUrl: 'searchdate.component.html',
  styleUrls: ['searchdate.component.css'],
})
export class SearchDateInvoiceComponent implements OnInit {

	constructor(public customerService:CustomerService, public router : Router,
				private route: ActivatedRoute, public invoiceService:InvoiceService) { }
	
	currentDateStart:string="";
	currentDateEnd:string="";
	
	customer:Customer = {} as Customer;
	
	searchValue:string="1";
	
	list:Invoice[] = [];
	
	searchInvoice:SearchInvoice = {} as SearchInvoice;
	
	private routeSub: Subscription;
	
	ngOnInit() {
		
		this.routeSub = this.route.params.subscribe(params => {
    		this.customerService.setCurrentIdSelected(params['idcustomer']);
  		});
		
  		this.getCustomerById();
  	}
	
	getCustomerById() {
		this.customerService.getCustomerByCurrentId().subscribe({
	        next: data => {
				this.customer = data;
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("invoice/error");
	        }
      	});
	}
	
  	getSearchCustomers() {
		if(this.currentDateStart != "" && this.currentDateEnd != "") {
			
			this.searchInvoice = {
			    idCustomer:this.customer.id,
	  			startDate:this.currentDateStart,
	  			endDate:this.currentDateEnd,
			}
			
			this.invoiceService.searchInvoicesByDate(this.searchInvoice).subscribe({
		        next: data => {
					this.list = data;
		        },
		        error: error => {
		            console.error('There was an error!', error);
		            this.router.navigateByUrl("invoice/error");
		        }
	      	}); 
		}
	}

}
