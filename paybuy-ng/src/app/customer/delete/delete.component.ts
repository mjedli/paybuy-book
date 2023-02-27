import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../service.component';
import { Customer } from '../model/customer';

@Component({
  selector: 'app-deletecustomer',
  templateUrl: 'delete.component.html',
  styleUrls: ['delete.component.css'],
})
export class DeleteCustomerComponent implements OnInit {

	constructor(public customerService:CustomerService, public router : Router, private route: ActivatedRoute) { }
	
	searchValue:string="";
	
	customer:Customer = {} as Customer;
	
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
	            this.router.navigateByUrl("customer/error");
	        }
      	});
 	}

  	deleteCustomer() {
		this.customerService.removeCustomer(this.customer).subscribe({
	        next: data => {
				if(data == 1) {
					this.router.navigateByUrl("customer/success");
				} else {
					this.router.navigateByUrl("customer/error");
				}
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("customer/error");
	        }
      	});
		
 	}

}
