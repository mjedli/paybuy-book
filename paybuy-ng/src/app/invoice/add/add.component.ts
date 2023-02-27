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
  selector: 'app-addinvoice',
  templateUrl: 'add.component.html',
  styleUrls: ['add.component.css'],
})
export class AddInvoiceComponent implements OnInit {

	constructor(public stockService:StockService, public customerService:CustomerService,
				public router : Router, private route: ActivatedRoute,
				public invoiceService:InvoiceService) { }
	
	searchValue:string="";
	errorMessage:string="";
	
	currentDate = new Date();;
	
	somme:number=0.00;
	sommeTVA:number=0.00;
	TVA:number=0.00;
	paid:number=0.00;
	credit:number=0.00;
	
	customer:Customer = {} as Customer;
	
	invoice:Invoice = {} as Invoice;
	
	lineTemp:Line = {} as Line;
	lineTempOrigine:Line = {} as Line;
	
	list:Product[] = [];
	
	listline:Line[] = [];
	
	listSearchLine:Line[] = [];
	
	listSearchLineOrigine:Line[] = [];
	
	private routeSub: Subscription;
	
	ngOnInit() {
		
		this.routeSub = this.route.params.subscribe(params => {
    		this.customerService.setCurrentIdSelected(params['idcustomer']);
  		});
		
		this.getCustomerById();
  		this.getSearchCustomers();
  	}
  	
  	saveInvoice() {
		this.invoice = {
		  id:"",
		  idCustomer:this.customer.id,
		  date:this.currentDate,
		  listline:this.listline,
		  total:this.somme,
		  totalTva:this.sommeTVA,
		  credit:this.customer.credit,
		  newCredit:this.credit,
		  paid:this.paid
		}
		
		this.invoiceService.addInvoice(this.invoice).subscribe({
	        next: data => {
				this.router.navigateByUrl("invoice/success");
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("invoice/error");
	        }
      	});
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
	
	resetSearchLine() {
		this.listSearchLine = [];
		this.listSearchLineOrigine = [];
	}
	
	saveLine(id:string) {
		this.lineTemp = this.listSearchLine.find(x => x.id == id)!;
		this.lineTempOrigine = this.listSearchLineOrigine.find(y => y.id == id)!;
		if(this.lineTemp.amount<=this.lineTempOrigine.amount) {
			this.lineTemp.price = Number(this.lineTemp.amount)*Number(this.lineTemp.sellPrice);
			this.listline.push(this.lineTemp);
			this.somme=this.somme+this.lineTemp.price;
			this.sommeTVA=this.sommeTVA+this.lineTemp.price+((this.lineTemp.price*Number(this.lineTemp.tva))/100);
			this.listSearchLine = [];
			this.listSearchLineOrigine = [];
			this.errorMessage="";
		} else {
			this.errorMessage="Quantité erreur!";
		}
		

	}
	
	calCredit() {
		if(this.paid==this.sommeTVA) {
			this.credit=Number(this.customer.credit);
		} else {
			if(this.paid<this.sommeTVA) {
				this.credit=Number(this.sommeTVA)-Number(this.paid)+Number(this.customer.credit);
			} else if(this.paid>this.sommeTVA) {
				this.credit=Number(this.customer.credit)-(Number(this.paid)-Number(this.sommeTVA));
			} 
		}

		
	}
	
	removeLine(id:string) {
		
		this.lineTemp = this.listline.find(x => x.id == id)!;
		this.somme=this.somme-this.lineTemp.price;
		this.sommeTVA=this.sommeTVA-this.lineTemp.price-((this.lineTemp.price*Number(this.lineTemp.tva))/100);
		
		const index = this.listline.findIndex((e) => e.id === id);

	    if (index !== -1) {
	        this.listline.splice(index, 1);
	    }
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
	
  	getSearchCustomers() {
		if(this.searchValue != "") {
			this.stockService.setSearchValue(this.searchValue);
			if(this.searchValue != "") {
				 this.stockService.setSearchValue(this.searchValue);
			   	 this.stockService.getSearchProductNotEmpty().subscribe({
			        next: data => {
			            this.list = data;
			            for (var i = 0; i < this.list.length; i++) {

							this.lineTemp = this.listline.find(z => z.id == this.list[i].id)!;
							
							if(this.lineTemp == undefined) {
								this.lineTemp = { id:this.list[i].id,
												  idProvider:this.list[i].idProvider,
												  name:this.list[i].name,
												  amount:Number(this.list[i].amount),
												  sellPrice:this.list[i].sellPrice,
												  tva:this.list[i].tva,
												  price:0,	
												};
							  	this.listSearchLine.push(this.lineTemp);
							  	
							  	this.lineTempOrigine = { id:this.list[i].id,
												  idProvider:this.list[i].idProvider,
												  name:this.list[i].name,
												  amount:Number(this.list[i].amount),
												  sellPrice:this.list[i].sellPrice,
												  tva:this.list[i].tva,
												  price:0,	
												};
							  	
							  	this.listSearchLineOrigine.push(this.lineTempOrigine);
							}
							
						}
			        },
			        error: error => {
			            console.error('There was an error!', error);
			            this.router.navigateByUrl("invoice/error");
			        }
				});
			}
		}
 	}

}
