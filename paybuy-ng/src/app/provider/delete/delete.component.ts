import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ProviderService } from '../service.component';
import { Provider } from '../model/provider';

@Component({
  selector: 'app-deleteprovider',
  templateUrl: 'delete.component.html',
  styleUrls: ['delete.component.css'],
})
export class DeleteProviderComponent implements OnInit {

	constructor(public providerService:ProviderService, public router : Router, private route: ActivatedRoute) { }
	
	searchValue:string="";
	
	provider:Provider = {} as Provider;
	
	private routeSub: Subscription;
	
	ngOnInit() {
  		this.routeSub = this.route.params.subscribe(params => {
    		this.providerService.setCurrentIdSelected(params['idprovider']);
  		});
  		
  		this.getProviderById();
  	}
	
  	getProviderById() {
		this.providerService.getProviderByCurrentId().subscribe({
	        next: data => {
				this.provider = data;
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("customer/error");
	        }
      	});
 	}

  	deleteProvider() {
		this.providerService.removeProvider(this.provider).subscribe({
	        next: data => {
				if(data == 1) {
					this.router.navigateByUrl("provider/success");
				} else {
					this.router.navigateByUrl("provider/error");
				}
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("provider/error");
	        }
      	});
		
 	}

}
