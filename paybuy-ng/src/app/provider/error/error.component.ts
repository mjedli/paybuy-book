import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../service.component';

@Component({
  selector: 'app-errorprovider',
  templateUrl: 'error.component.html',
  styleUrls: ['error.component.css'],
})
export class ErrorProviderComponent implements OnInit {

	constructor(public providerService:ProviderService) { }
	
	
	ngOnInit() {

  	}


}
