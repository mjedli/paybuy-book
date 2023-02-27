import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StockService } from '../service.component';
import { Product } from '../model/product';

@Component({
  selector: 'app-searchproduct',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css'],
})
export class SearchProductComponent implements OnInit {

	constructor(public stockService:StockService, public router : Router) { }
	
	searchValue:string="";
	
	list:Product[] = [];
	
	ngOnInit() {
  		this.getSearchCustomers();
  	}
	
  	getSearchCustomers() {
		if(this.searchValue != "") {
			 this.stockService.setSearchValue(this.searchValue);
		   	 this.stockService.getSearchProduct().subscribe({
		        next: data => {
		            this.list = data;
		        },
		        error: error => {
		            console.error('There was an error!', error);
		            this.router.navigateByUrl("stock/error");
		        }
			});
		}
 	}

}
