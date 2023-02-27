import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProviderService } from '../service.component';
import { Provider } from '../model/provider';

@Component({
  selector: 'app-searchprovider',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css'],
})
export class SearchProviderComponent implements OnInit {

	constructor(public providerService:ProviderService, public router : Router) { }
	
	searchValue:string="";
	
	list:Provider[] = [];
	
	ngOnInit() {
  		this.getSearchProviders();
  	}
	
  	getSearchProviders() {
		if(this.searchValue != "") {
			 this.providerService.setSearchValue(this.searchValue);
		   	 this.providerService.getSearchProviders().subscribe({
		        next: data => {
		            this.list = data;
		        },
		        error: error => {
		            console.error('There was an error!', error);
		            this.router.navigateByUrl("provider/error");
		        }
			});
		}
 	}

}
