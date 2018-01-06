import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from "@angular/http";

import {AppRoutingModule} from "./app-routing.module";

// import {FileSelectDirective, FileDropDirective} from "ng2-file-upload/ng2-file-upload";

import {AppComponent } from './app.component';
import {HomeComponent} from "./home/home.component";
import {AddArticleComponent} from "./addArticle/add-article.component";
import {ViewArticleComponent} from "./viewArticle/view-article.component";

import {ArticleService} from "./_services/article.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddArticleComponent,
    ViewArticleComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
