import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../service.component';
import { Customer } from '../model/customer';

@Component({
  selector: 'app-detailscustomer',
  templateUrl: 'details.component.html',
  styleUrls: ['details.component.css'],
})
export class DetailsCustomerComponent implements OnInit {

	constructor(public customerService:CustomerService, private route: ActivatedRoute, public router : Router) { }
	
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

}
