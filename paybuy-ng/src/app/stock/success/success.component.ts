import { Component, OnInit } from '@angular/core';
import { StockService } from '../service.component';

@Component({
  selector: 'app-successproduct',
  templateUrl: 'success.component.html',
  styleUrls: ['success.component.css'],
})
export class SuccessProductComponent implements OnInit {

	constructor(public stockService:StockService) { }
	
	
	ngOnInit() {

  	}


}
