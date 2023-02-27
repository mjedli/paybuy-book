import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../service.component';
import { Customer } from '../model/customer';

@Component({
  selector: 'app-addcustomer',
  templateUrl: 'add.component.html',
  styleUrls: ['add.component.css'],
})
export class AddCustomerComponent {

	constructor(public customerService:CustomerService, public router : Router) { }

	currentCustomer:Customer  =  
	{id : '', name : '', firstname : '', lastname : '', birthday : '', mobile : '', address : '', credit:0};
	
	

  	addCustomer() {
		if(this.currentCustomer.name != '' && this.currentCustomer.firstname != '' && this.currentCustomer.lastname != '') {
			this.customerService.addCustomer(this.currentCustomer).subscribe({
		        next: data => {
					this.router.navigateByUrl("customer/success");
		            //this.postId = data.id;
		        },
		        error: error => {
		            console.error('There was an error!', error);
		            this.router.navigateByUrl("customer/error");
		        }
	      });
		}
 	}

}
