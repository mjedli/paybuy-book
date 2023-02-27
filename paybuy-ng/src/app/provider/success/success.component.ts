import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../service.component';

@Component({
  selector: 'app-successprovider',
  templateUrl: 'success.component.html',
  styleUrls: ['success.component.css'],
})
export class SuccessProviderComponent implements OnInit {

	constructor(public providerService:ProviderService) { }
	
	
	ngOnInit() {

  	}


}
