import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstPageComponent } from './zipZapZoo/firstPage.component';
import { AboutComponent } from './about/about.component';
import { ClientsComponent } from './clients/clients.component';
import { ContactUsComponent } from './contactUs/contactUs.component';
import { ServicesComponent } from './services/services.component';
import { CreateComponent } from './create/create.component';
import { DeleteComponent } from './services/delete/delete.component';
import { DepositComponent } from './services/deposit/deposit.component';
import { FundTransferComponent } from './services/fundTransfer/fundTransfer.component';
import { ShowBalanceComponent } from './services/showBalance/showBalance.component';
import { TransactionsComponent } from './services/transactions/transactions.component';
import { UpdateComponent } from './services/update/update.component';
import { WithdrawComponent } from './services/withdraw/withdraw.component';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    AboutComponent,
    ClientsComponent,
    ContactUsComponent,
    CreateComponent,
    ServicesComponent,
    routingComponents,
    CreateComponent,
    DeleteComponent,
    DepositComponent,
    FundTransferComponent,
    ShowBalanceComponent,
    TransactionsComponent,
    UpdateComponent,
    WithdrawComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
