import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-deposit',
    templateUrl: './deposit.component.html',
    
})

export class DepositComponent implements OnInit{

    onClick(accNum, amt){
        console.log(accNum.value);
        console.log(amt.value);
    }

    constructor(){}
    ngOnInit(){}

}