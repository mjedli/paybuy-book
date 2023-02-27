import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuComponent } from './menu/menu.component';
import { AddCustomerComponent } from './customer/add/add.component';
import { SearchCustomerComponent } from './customer/search/search.component';
import { DetailsCustomerComponent } from './customer/details/details.component';
import { UpdateCustomerComponent } from './customer/update/update.component';
import { DeleteCustomerComponent } from './customer/delete/delete.component';
import { SuccessCustomerComponent } from './customer/success/success.component';
import { ErrorCustomerComponent } from './customer/error/error.component';

import { AddProductComponent } from './stock/add/add.component';
import { SearchAddProductComponent } from './stock/searchadd/searchadd.component';
import { SearchProductComponent } from './stock/search/search.component';
import { EmptyProductComponent } from './stock/empty/empty.component';
import { DetailsProductComponent } from './stock/details/details.component';
import { UpdateProductComponent } from './stock/update/update.component';
import { DeleteProductComponent } from './stock/delete/delete.component';
import { SuccessProductComponent } from './stock/success/success.component';
import { ErrorProductComponent } from './stock/error/error.component';

import { SearchAddInvoiceComponent } from './invoice/searchadd/searchadd.component';
import { SearchDateInvoiceComponent } from './invoice/searchdate/searchdate.component';
import { AddInvoiceComponent } from './invoice/add/add.component';
import { SuccessInvoiceComponent } from './invoice/success/success.component';
import { SearchInvoiceComponent } from './invoice/search/search.component';
import { InvoiceComponent } from './invoice/invoice/invoice.component';
import { ErrorInvoiceComponent } from './invoice/error/error.component';
import { ResultInvoiceComponent } from './invoice/result/result.component';

import { AddProviderComponent } from './provider/add/add.component';
import { SuccessProviderComponent } from './provider/success/success.component'
import { ErrorProviderComponent } from './provider/error/error.component';
import { SearchProviderComponent } from './provider/search/search.component';
import { DetailsProviderComponent } from './provider/details/details.component';
import { DeleteProviderComponent } from './provider/delete/delete.component';
import { UpdateProviderComponent } from './provider/update/update.component';

const routes: Routes = [
	
	{ path: '', redirectTo:'menu', pathMatch: 'full' },
	{ path: 'menu', component: MenuComponent },
	
 	{ path: 'customer/add', component: AddCustomerComponent },
 	{ path: 'customer/search', component: SearchCustomerComponent },
 	{ path: 'customer/details/:idcustomer', component: DetailsCustomerComponent },
 	{ path: 'customer/update/:idcustomer', component: UpdateCustomerComponent },
 	{ path: 'customer/delete/:idcustomer', component: DeleteCustomerComponent },
 	{ path: 'customer/success', component: SuccessCustomerComponent },
 	{ path: 'customer/error', component: ErrorCustomerComponent },
 	
 	{ path: 'provider/add', component: AddProviderComponent },
 	{ path: 'provider/search', component: SearchProviderComponent },
 	{ path: 'provider/success', component: SuccessProviderComponent },
 	{ path: 'provider/error', component: ErrorProviderComponent },
 	{ path: 'provider/details/:idprovider', component: DetailsProviderComponent },
  	{ path: 'provider/delete/:idprovider', component: DeleteProviderComponent },
  	{ path: 'provider/update/:idprovider', component: UpdateProviderComponent },
  		 	 	
 	{ path: 'stock/product/add', component: SearchAddProductComponent },
 	{ path: 'stock/product/add/:idprovider', component: AddProductComponent },
 	{ path: 'stock/product/search', component: SearchProductComponent },
 	{ path: 'stock/product/empty', component: EmptyProductComponent },
 	{ path: 'stock/product/details/:idproduct', component: DetailsProductComponent },
 	{ path: 'stock/product/update/:idproduct', component: UpdateProductComponent },
 	{ path: 'stock/product/delete/:idproduct', component: DeleteProductComponent },
 	{ path: 'stock/product/success', component: SuccessProductComponent },
 	{ path: 'stock/product/error', component: ErrorProductComponent },
 	
 	{ path: 'invoice/add', component: SearchAddInvoiceComponent },
 	{ path: 'invoice/add/:idcustomer', component: AddInvoiceComponent },
 	{ path: 'invoice/search', component: SearchInvoiceComponent },
 	{ path: 'invoice/search/:idcustomer', component: SearchDateInvoiceComponent },
 	{ path: 'invoice/success', component: SuccessInvoiceComponent },
 	{ path: 'invoice/error', component: ErrorInvoiceComponent },
 	{ path: 'invoice/:idcustomer/:idinvoice', component: InvoiceComponent },
 	{ path: 'invoice/result', component: ResultInvoiceComponent },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
