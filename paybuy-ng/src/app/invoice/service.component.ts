import { Injectable } from '@angular/core';
import { Customer } from '../customer/model/customer';
import { Invoice } from './model/invoice';
import { Result } from './model/result';
import { SearchInvoice } from './model/SearchInvoice';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

// #docregion http-options
const httpOptions = {
  headers: new HttpHeaders({
	'Access-Control-Allow-Origin':'*',
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable()
export class InvoiceService {

  	private usersUrl: string;

 	constructor(private http: HttpClient) {
    	this.usersUrl = "http://localhost:8080"; //globalThis.customEnvironment.api.url;
 	}

  listInvoice:Invoice[] = [];

  currentIdSelected:string = "0";
  searchValue:string = "";

  /*
  * setSearchValue
  */
  public setSearchValue(searchValue:string) {
    this.searchValue = searchValue;
  }

  /*
  * setCurrentIdSelected
  */
  public setCurrentIdSelected(currentNumber:string) {
    this.currentIdSelected = currentNumber;
  }


  /*
  * addComponent
  */
  public addInvoice(invoice:Invoice):Observable<Invoice> {
    return this.http.post<Invoice>(this.usersUrl+"/paybay/invoice/add", invoice, httpOptions);
  }

  /*
  * getComponentByCurrentId
  */
  public getInvoiceByCurrentId():Observable<Invoice> {
    return this.http.get<Invoice>(this.usersUrl+"/paybay/invoice/"+this.currentIdSelected);
  }
  
  /*
  * getAllComponentByDate
  */
  public searchInvoicesByDate(searchInvoice:SearchInvoice):Observable<Invoice[]> {
    return this.http.post<Invoice[]>(this.usersUrl+"/paybay/invoice/date", searchInvoice, httpOptions);
  }
  
    /*
  * Cal result
  */
  public calResult(date:Date):Observable<Result> {
    return this.http.post<Result>(this.usersUrl+"/paybay/invoice/result", date, httpOptions);
  }
}