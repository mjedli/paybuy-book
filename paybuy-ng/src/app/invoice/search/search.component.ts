import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../customer/service.component';
import { Customer } from '../../customer/model/customer';

@Component({
  selector: 'app-searchinvoice',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css'],
})
export class SearchInvoiceComponent implements OnInit {

	constructor(public customerService:CustomerService, public router : Router) { }
	
	searchValue:string="";
	
	list:Customer[] = [];
	
	ngOnInit() {
  		this.getSearchCustomers();
  	}
	
  	getSearchCustomers() {
		if(this.searchValue != "") {
			 this.customerService.setSearchValue(this.searchValue);
		   	 this.customerService.getSearchCustomers().subscribe({
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
