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

@Component({
  selector: 'app-invoice',
  templateUrl: 'invoice.component.html',
  styleUrls: ['invoice.component.css'],
})
export class InvoiceComponent implements OnInit {

	constructor(public stockService:StockService, public customerService:CustomerService,
				public router : Router, private route: ActivatedRoute,
				public invoiceService:InvoiceService) { }
	
	searchValue:string="";
	
	currentDate = new Date();;
	
	somme:number=0.00;
	sommeTVA:number=0.00;
	TVA:number=0.00;
	paid:number=0.00;
	credit:number=0.00;
	newCredit:number=0.00;
	
	customer:Customer = {} as Customer;
	
	invoice:Invoice = {} as Invoice;
	
	lineTemp:Line = {} as Line;
	
	listline:Line[] = [];
	
	private routeSub: Subscription;
	
	ngOnInit() {
		
		this.routeSub = this.route.params.subscribe(params => {
    		this.customerService.setCurrentIdSelected(params['idcustomer']);
    		this.invoiceService.setCurrentIdSelected(params['idinvoice']);
  		});
		
		this.getCustomerById();
  		this.geInvoiceById();
  	}
	
	onPrint(divName:string) {
        var mywindow = window.open('', 'PRINT');
    
        mywindow!.document.write('<html lang="fr"><head><title></title>');
        mywindow!.document.write('<style>');
        
        mywindow!.document.write('@media print{@page {size: A4; margin-top: 0;margin-bottom: 0;}body  {padding-top: 72px;padding-bottom: 72px ;}}'); 

        mywindow!.document.write('.table .thead-light th {color: #495057;background-color: #e9ecef;border-color: #dee2e6;}');
        mywindow!.document.write('.table thead th {vertical-align: bottom;border-bottom: 2px solid #dee2e6;border-bottom-color: rgb(222, 226, 230);}');
        mywindow!.document.write('.table td, .table th {padding: .10rem;vertical-align: top;border-top: 1px solid #dee2e6;border-top-color: rgb(222, 226, 230);');
        
        mywindow!.document.write('th {text-align: inherit;} .table {color: #212529;} table {border-collapse: collapse;}');
        mywindow!.document.write(".material-icons {font-family: 'Material Icons';font-weight: normal;font-style: normal;font-size: 24px;line-height: 1;letter-spacing: normal;text-transform: none;");
		mywindow!.document.write("display: inline-block;white-space: nowrap;word-wrap: normal;direction: ltr;-moz-font-feature-settings: 'liga';-moz-osx-font-smoothing: grayscale;}");
		mywindow!.document.write('</style>');
		//mywindow!.document.write('<link rel="stylesheet" href="content/bootstrap.min.css"/>');
		//mywindow!.document.write('<link href="content/materialicons.css" type="text/css" rel="stylesheet">');
        mywindow!.document.write('</head><body >');
        mywindow!.document.write(document.getElementById(divName)!.innerHTML);
        mywindow!.document.write('</body></html>');
    
        mywindow!.document.close(); // necessary for IE >= 10
        mywindow!.focus(); // necessary for IE >= 10*/

        mywindow!.print();
        mywindow!.close();
        return false;
	    
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
	
  	geInvoiceById() {
		this.invoiceService.getInvoiceByCurrentId().subscribe({
	        next: data => {
		
				this.invoice = data;
				this.currentDate = this.invoice.date;
				this.somme = this.invoice.total;
				this.sommeTVA = this.invoice.totalTva;
				this.paid=this.invoice.paid;
				this.credit=this.invoice.credit;
				this.newCredit=this.invoice.newCredit;
				this.listline = this.invoice.listline;
				
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("invoice/error");
	        }
      	});
		

		
 	}

}
