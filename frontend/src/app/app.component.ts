import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  fileName = '';

  constructor(private http: HttpClient) {}

  onFileSelected(e:Event) {
    let event = e.target as HTMLInputElement;
      const file:File = event!.files![0];

      if (file) {

          this.fileName = file.name;

          const formData = new FormData();

          formData.append("thumbnail", file);

          const upload$ = this.http.post("http://localhost:8080/file?ordem=IDADE", formData,{
            headers:new HttpHeaders({"Access-Control-Allow-Origin": "*", "Content-Type": "multipart/form-data"})
          });

          upload$.subscribe();
      }
  }


}


