import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../service.component';

@Component({
  selector: 'app-errorinvoice',
  templateUrl: 'error.component.html',
  styleUrls: ['error.component.css'],
})
export class ErrorInvoiceComponent implements OnInit {

	constructor(public invoiceService:InvoiceService) { }
	
	
	ngOnInit() {

  	}


}
