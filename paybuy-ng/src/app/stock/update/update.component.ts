import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { StockService } from '../service.component';
import { Product } from '../model/product';

@Component({
  selector: 'app-productupdate',
  templateUrl: 'update.component.html',
  styleUrls: ['update.component.css'],
})
export class UpdateProductComponent implements OnInit {

	constructor(public stockService:StockService, public router : Router, private route: ActivatedRoute) { }
	
	searchValue:string="";
	
	product:Product = {} as Product;
	
	private routeSub: Subscription;
	
	ngOnInit() {
  		this.routeSub = this.route.params.subscribe(params => {
    		this.stockService.setCurrentIdSelected(params['idproduct']);
  		});
  		
  		this.getProductById();
  	}
	
  	getProductById() {
		this.stockService.getProductByCurrentId().subscribe({
	        next: data => {
				this.product = data;
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("stock/error");
	        }
      	});
 	}

  	updateProduct() {	
		this.stockService.modifiyProduct(this.product).subscribe({
	        next: data => {
				this.router.navigateByUrl("stock/product/success");
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("stock/error");
	        }
      	});
 	}

}
