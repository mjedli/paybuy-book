import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { StockService } from '../service.component';
import { Product } from '../model/product';

@Component({
  selector: 'app-deleteproduct',
  templateUrl: 'delete.component.html',
  styleUrls: ['delete.component.css'],
})
export class DeleteProductComponent implements OnInit {

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

  	deleteProduct() {
		this.stockService.removeProduct(this.product).subscribe({
	        next: data => {
				if(data == 1) {
					this.router.navigateByUrl("stock/product/success");
				} else {
					this.router.navigateByUrl("stock/error");
				}
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("stock/error");
	        }
      	})
 	}

}
