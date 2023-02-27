import { Component, OnInit  } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { StockService } from '../../stock/service.component';
import { CustomerService } from '../../customer/service.component';
import { InvoiceService } from '../service.component';
import { Product } from '../../stock/model/product';
import { Customer } from '../../customer/model/customer';
import { Line } from '../model/line';
import { Invoice } from '../model/invoice';
import { Result } from '../model/result';

@Component({
  selector: 'app-resultinvoice',
  templateUrl: 'result.component.html',
  styleUrls: ['result.component.css'],
})
export class ResultInvoiceComponent implements OnInit {

	constructor(public stockService:StockService, public customerService:CustomerService,
				public router : Router, private route: ActivatedRoute,
				public invoiceService:InvoiceService) { }
	
	resultDay:string="";
	resultMonth:string="";
	resultYear:string="";
	
	currentDate:Date;
	
	ngOnInit() {
		this.currentDate = new Date();
		this.calResult();
  	}
  	
  	calResult() {
		this.invoiceService.calResult(new Date(this.currentDate)).subscribe({
	        next: data => {
				this.resultDay=data.resultDay;
				this.resultMonth=data.resultMonth;
				this.resultYear=data.resultYear;
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("invoice/error");
	        }
	  	});
	}

}
