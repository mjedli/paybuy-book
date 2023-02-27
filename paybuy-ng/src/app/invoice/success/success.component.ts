import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../service.component';

@Component({
  selector: 'app-successinvoice',
  templateUrl: 'success.component.html',
  styleUrls: ['success.component.css'],
})
export class SuccessInvoiceComponent implements OnInit {

	constructor(public invoiceService:InvoiceService) { }
	
	
	ngOnInit() {

  	}


}
