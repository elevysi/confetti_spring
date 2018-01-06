import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

import {ArticleService} from "../_services/article.service";

import {Article} from "../_models/article";

@Component({
    moduleId: module.id,
    selector : "add-article",
    templateUrl : "add-article.component.html"
})

export class AddArticleComponent implements OnInit{

    private model: any = {};
    
    constructor(
        private articleService: ArticleService
    ){

    }

    ngOnInit(): void{
        //Set UUID

        this.articleService.initArticleAdd()
        .then(data => {
            if(data.success == 1) this.model.uuid = data.uuid;
            
        });
    }
}