import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import {HomeComponent} from "./home/home.component";
import {AddArticleComponent} from "./addArticle/add-article.component";
import {ViewArticleComponent} from "./viewArticle/view-article.component";

const routes : Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'addArticle',
        component: AddArticleComponent
    },
    {
        path: 'view/:id',
        component: ViewArticleComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule{

}