import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ProviderService } from '../service.component';
import { Provider } from '../model/provider';

@Component({
  selector: 'app-updateprovider',
  templateUrl: 'update.component.html',
  styleUrls: ['update.component.css'],
})
export class UpdateProviderComponent implements OnInit {

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
	            this.router.navigateByUrl("provider/error");
	        }
      	});
 	}

  	updateProvider() {	
		this.providerService.modifiyProvider(this.provider).subscribe({
	        next: data => {
				this.router.navigateByUrl("provider/success");
	        },
	        error: error => {
	            console.error('There was an error!', error);
	            this.router.navigateByUrl("provider/error");
	        }
      	});
 	}

}
