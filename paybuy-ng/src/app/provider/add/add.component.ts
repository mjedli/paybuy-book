import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProviderService } from '../service.component';
import { Provider } from '../model/provider';

@Component({
  selector: 'app-addprovider',
  templateUrl: 'add.component.html',
  styleUrls: ['add.component.css'],
})
export class AddProviderComponent {

	constructor(public providerService:ProviderService, public router : Router) { }

	currentProvider:Provider  =  
	{id : '', name : '', firstname : '', lastname : '', birthday : '', mobile : '', address : ''};
	
  	addProvider() {
		if(this.currentProvider.name != '' && this.currentProvider.firstname != '' && this.currentProvider.lastname != '') {
			this.providerService.addProvider(this.currentProvider).subscribe({
		        next: data => {
					this.router.navigateByUrl("provider/success");
		            //this.postId = data.id;
		        },
		        error: error => {
		            console.error('There was an error!', error);
		            this.router.navigateByUrl("provider/error");
		        }
	      });
		}
 	}

}
