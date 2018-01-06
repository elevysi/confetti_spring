import {Component, OnInit} from '@angular/core';
import { Router } from "@angular/router";

import {Article} from "../_models/article";
import {ArticleService} from "../_services/article.service";

@Component({
    moduleId: module.id,
    selector: "shop-home",
    templateUrl : "home.component.html"
})

export class HomeComponent implements OnInit{
    
    constructor(
        private articleService : ArticleService,
        private router : Router
    ){};

    ngOnInit(): void {
        this.articleService.getArticles()
        .then(articles => {

        });
    }

}