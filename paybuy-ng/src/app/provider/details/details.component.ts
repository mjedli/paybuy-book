import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ProviderService } from '../service.component';
import { Provider } from '../model/provider';

@Component({
  selector: 'app-detailsprovider',
  templateUrl: 'details.component.html',
  styleUrls: ['details.component.css'],
})
export class DetailsProviderComponent implements OnInit {

	constructor(public providerService:ProviderService, private route: ActivatedRoute, public router : Router) { }
	
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

}
