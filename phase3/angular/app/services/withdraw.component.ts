import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-withdraw',
    templateUrl: './withdraw.component.html',
    
})

export class WithdrawComponent implements OnInit{

    onClick(accNum, amt){
        console.log(accNum.value);
        console.log(amt.value);
    }


    constructor(){}
    ngOnInit(){}

}