import { Injectable } from '@angular/core';
import { Product } from './model/product';
import { Customer } from '../customer/model/customer';
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
export class StockService {

  	private usersUrl: string;

 	constructor(private http: HttpClient) {
    	this.usersUrl = "http://localhost:8080"; //globalThis.customEnvironment.api.url;
 	}

  listProduct  : Product[] = [];
  
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
	public addProduct(product: Product):Observable<Product> {
	   	return this.http.post<Product>(this.usersUrl+"/paybay/stock/add", product, httpOptions);
	}
  
  /*
  * getComponentByCurrentId
  */
  public getProductByCurrentId():Observable<Product> {
    return this.http.get<Product>(this.usersUrl+"/paybay/stock/"+this.currentIdSelected);
  }

  /*
  * modifiyComponent
  */

  public modifiyProduct(product:Product):Observable<Product> {
    return this.http.post<Product>(this.usersUrl+"/paybay/stock/update", product, httpOptions);
  }

  /*
  * removeComponent
  */

  public removeProduct(product:Product):Observable<number> {
    return this.http.post<number>(this.usersUrl+"/paybay/stock/remove", product, httpOptions);
  }

  /*
  * getAllComponent
  */
  public getSearchProduct():Observable<Product[]> {
    return this.http.post<Product[]>(this.usersUrl+"/paybay/stock", this.searchValue, httpOptions);
  }
  
  /*
  * getAllComponent with 0 amount
  */
  public getSearchProductNotEmpty():Observable<Product[]> {
    return this.http.post<Product[]>(this.usersUrl+"/paybay/stock/notempty", this.searchValue, httpOptions);
  }
  
  /*
  * getSearchEmptyProduct
  */
  public getSearchEmptyProduct():Observable<Product[]> {
    return this.http.get<Product[]>(this.usersUrl+"/paybay/stock/empty",httpOptions);
  }

}