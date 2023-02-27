import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../service.component';

@Component({
  selector: 'app-errorcustomer',
  templateUrl: 'error.component.html',
  styleUrls: ['error.component.css'],
})
export class ErrorCustomerComponent implements OnInit {

	constructor(public customerService:CustomerService) { }
	
	
	ngOnInit() {

  	}


}
