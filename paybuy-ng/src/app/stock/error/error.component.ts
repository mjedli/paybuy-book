import { Component, OnInit } from '@angular/core';
import { StockService } from '../service.component';

@Component({
  selector: 'app-errorproduct',
  templateUrl: 'error.component.html',
  styleUrls: ['error.component.css'],
})
export class ErrorProductComponent implements OnInit {

	constructor(public stockService:StockService) { }
	
	
	ngOnInit() {

  	}


}
