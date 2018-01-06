import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

import {Article} from "../_models/article";

@Component({
    moduleId: module.id,
    selector : "view-article",
    templateUrl : "view-article.component.html"
})

export class ViewArticleComponent implements OnInit{
    ngOnInit(): void{

    }
}