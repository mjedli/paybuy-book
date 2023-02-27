import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../service.component';

@Component({
  selector: 'app-successcustomer',
  templateUrl: 'success.component.html',
  styleUrls: ['success.component.css'],
})
export class SuccessCustomerComponent implements OnInit {

	constructor(public customerService:CustomerService) { }
	
	
	ngOnInit() {

  	}


}
