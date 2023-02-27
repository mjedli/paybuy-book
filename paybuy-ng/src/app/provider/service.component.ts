import { Injectable } from '@angular/core';
import { Provider } from './model/provider';
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
export class ProviderService {

  	private usersUrl: string;

 	constructor(private http: HttpClient) {
    	this.usersUrl = "http://localhost:8080"; //globalThis.customEnvironment.api.url;
 	}

  listProvider  : Provider[] = [];

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
  public addProvider(provider:Provider):Observable<Provider> {
    return this.http.post<Provider>(this.usersUrl+"/paybay/provider/add", provider, httpOptions);

  }
  
  /*
  * getComponentByCurrentId
  */
  public getProviderByCurrentId():Observable<Provider> {
    return this.http.get<Provider>(this.usersUrl+"/paybay/provider/"+this.currentIdSelected);
  }

  /*
  * modifiyComponent
  */
  public modifiyProvider(provider:Provider):Observable<Provider> {
    return this.http.post<Provider>(this.usersUrl+"/paybay/provider/update", provider, httpOptions);
  }

  /*
  * removeComponent
  */
  public removeProvider(provider:Provider):Observable<number> {
    return this.http.post<number>(this.usersUrl+"/paybay/provider/remove", provider, httpOptions);
  }

  /*
  * getAllComponent
  */
  public getSearchProviders():Observable<Provider[]> {
    return this.http.post<Provider[]>(this.usersUrl+"/paybay/provider", this.searchValue, httpOptions);
  }
}