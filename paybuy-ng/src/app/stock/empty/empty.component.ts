import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StockService } from '../service.component';
import { Product } from '../model/product';

@Component({
  selector: 'app-emptyproduct',
  templateUrl: 'empty.component.html',
  styleUrls: ['empty.component.css'],
})
export class EmptyProductComponent implements OnInit {

	constructor(public stockService:StockService, public router : Router) { }
	
	searchValue:string="";
	
	list:Product[] = [];
	
	ngOnInit() {
  		this.getSearchEmptyProduct();
  	}
	
  	getSearchEmptyProduct() {
	   	 this.stockService.getSearchEmptyProduct().subscribe({
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
