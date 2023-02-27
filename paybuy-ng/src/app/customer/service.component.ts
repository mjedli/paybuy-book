import { Injectable } from '@angular/core';
import { Customer } from './model/customer';
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

@Injectable({providedIn: 'root'})
export class CustomerService {

  	private usersUrl: string;

 	constructor(private http: HttpClient) {
    	this.usersUrl = "http://localhost:8080"; //globalThis.customEnvironment.api.url;
 	}

  list  : Customer[] = [];

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
  public addCustomer(customer:Customer):Observable<Customer> {
    return this.http.post<Customer>(this.usersUrl + "/paybay/customer/add", customer, httpOptions);

  }
  
  /*
  * getComponentByCurrentId
  */
  public getCustomerByCurrentId():Observable<Customer> {
    return this.http.get<Customer>(this.usersUrl + "/paybay/customer/"+this.currentIdSelected);
  }

  /*
  * modifiyComponent
  */
  public modifiyCustomer(customer:Customer):Observable<Customer> {
    return this.http.post<Customer>(this.usersUrl + "/paybay/customer/update", customer, httpOptions);
  }

  /*
  * removeComponent
  */
  public removeCustomer(customer:Customer):Observable<number> {
    return this.http.post<number>(this.usersUrl + "/paybay/customer/remove", customer, httpOptions);
  }

  /*
  * getAllComponent
  */
  public getSearchCustomers():Observable<Customer[]> {
    return this.http.post<Customer[]>(this.usersUrl + "/paybay/customer", this.searchValue, httpOptions);
  }
}