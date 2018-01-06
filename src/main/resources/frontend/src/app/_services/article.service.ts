import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import {Observable} from "rxjs";
import 'rxjs/add/operator/toPromise';

import {Article} from "../_models/article";

@Injectable()
export class ArticleService{

    private articleUrl = "api/shop/";

    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http){}

    getArticles(): Promise<Article[]> {
        return this.http.get(this.articleUrl)
        .toPromise()
        .then(response => response.json() as Article[])
        .catch(this.handleError);
    }

    initArticleAdd(): Promise<any>{
        const url = `${this.articleUrl}/addArticleInit`;
        return this.http.get(url)
        .toPromise()
        .then(response => {
            var data = response.json();
            return data;
        });

    }
    

    getArticle(id : String): Promise<Article> {
        const url = `${this.articleUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => {
                   var article : Article = response.json();
                   return article;
            })
            .catch(this.handleError);
    }




    private handleError(error : any) : Promise<any> {
        console.error('An error has occured', error);
        return Promise.reject(error.message || error);
    }
}