import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactUsComponent } from './contactUs/contactUs.component';
import { ClientsComponent } from './clients/clients.component';
import { ServicesComponent } from './services/services.component';
import { AboutComponent } from './about/about.component';
import { FirstPageComponent } from './zipZapZoo/firstPage.component';
import { CreateComponent } from './create/create.component';
import { DeleteComponent } from './services/delete/delete.component';
import { DepositComponent } from './services/deposit/deposit.component';
import { FundTransferComponent } from './services/fundTransfer/fundTransfer.component';
import { ShowBalanceComponent } from './services/showBalance/showBalance.component';
import { TransactionsComponent } from './services/transactions/transactions.component';
import { UpdateComponent } from './services/update/update.component';
import { WithdrawComponent } from './services/withdraw/withdraw.component';


const routes: Routes = [
  {path: 'firstPage', component: FirstPageComponent},
  {path: 'contactUs', component: ContactUsComponent},
  {path: 'clients', component: ClientsComponent},
  {path: 'services', component: ServicesComponent},
  {path: 'about', component: AboutComponent},
  {path: 'create', component: CreateComponent},
  {path: 'delete', component: DeleteComponent },
  {path: 'deposit', component: DepositComponent},
  {path: 'fundTransfer', component: FundTransferComponent},
  {path: 'showBalance', component: ShowBalanceComponent},
  {path: 'transactions', component: TransactionsComponent},
  {path: 'update', component: UpdateComponent},
  {path: 'withdraw', component: WithdrawComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents= [AboutComponent, FirstPageComponent, 
             ContactUsComponent,ClientsComponent,
             ServicesComponent ];
