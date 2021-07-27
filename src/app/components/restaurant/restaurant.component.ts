import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Restaurant } from 'src/app/models/restaurant.model';
import { RestaurantService } from 'src/app/services/restaurant.service';


@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  @Input()
  public restaurants: Restaurant[];
  public editRestaurant: Restaurant;
  public deleteRestaurant: Restaurant;


  image: any;
  private selectedFile;

  @Output()
  AddedEvent = new EventEmitter();

  /*  
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  */

  constructor(
    private restaurantService: RestaurantService,
    private http: HttpClient) { }


  totalLength: number;
  page: number = 1;

  ngOnInit(): void {
    this.getRestaurants();
  }

  public getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe(
      (response: Restaurant[]) => {
        this.restaurants = response;
        this.totalLength = response.length;
        console.log(this.restaurants);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


public onFileChanged(event) {
  console.log(event);
  this.selectedFile = event.target.files[0];

  let reader = new FileReader();
  reader.readAsDataURL(event.target.files[0]);
  reader.onload = (event2) => {
    this.image = reader.result;
  }
}


/*
  public onAddRestaurant(addForm: NgForm): void {

    document.getElementById('add-restaurant-form').click();

    this.restaurantService.addRestaurant(addForm.value).subscribe(
      (response: Restaurant) => {
        console.log(response);
        this.getRestaurants();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
  */

  public onAddRestaurant(addForm: NgForm): void {

   document.getElementById('add-restaurant-form').click();

      const uploadData = new FormData();
      uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
//      this.selectedFile.resImage = this.selectedFile.name;

      this.http.post('http://localhost:8080/restaurant/upload', uploadData, {observe: 'response'})
        .subscribe((response) => {
          if(response.status === 200) {
            console.log('Image uploadded successfully');
          } else {
            console.log('Image not uploaded successfully');
          }
        });

        this.restaurantService.addRestaurant(addForm.value).subscribe(
          (response: Restaurant) => {
            console.log(response);
            this.getRestaurants();
            this.AddedEvent.emit();
            addForm.reset();
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
            addForm.reset();
          }
        );

  }
  

  public onUpdateRestaurant(restaurant: Restaurant): void {
    const updateImage = new FormData();
    updateImage.append('imageUpdate', this.selectedFile, this.selectedFile.name);
    this.http.put('http://localhost:8080/restaurant/uploadud', updateImage, {observe: 'response'})
        .subscribe((response) => {
/*          if(response.status === 200) {
            console.log('Image uploadded successfully');
          } else {
            console.log('Image not uploaded successfully');
          }
*/
        console.log(response);  
        });

    this.restaurantService.updateRestaurant(restaurant).subscribe(
      (response: Restaurant) => {
        console.log(response);
        this.getRestaurants();
        this.AddedEvent.emit();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteRestaurant(restaurantId: number): void {
    this.restaurantService.deleteRestaurant(restaurantId).subscribe(
      (response: void) => {
        console.log(response);
        this.getRestaurants();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchRestaurants(key: string): void {
    console.log(key);
    const results: Restaurant[] = [];
    for (const restaurant of this.restaurants) {
      if (restaurant.resName.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || restaurant.registrationId
       || restaurant.canton.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || restaurant.district.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || restaurant.province.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || restaurant.resEmail.toLowerCase().indexOf(key.toLowerCase()) !== -1
       || restaurant.resTelephone.toLowerCase().indexOf(key.toLowerCase()) !== -1) {     
       results.push(restaurant);
      }
    }
    this.restaurants = results;
    if(results.length === 0 || !key) {
      this.getRestaurants();
    }
  }

  public onOpenModal(restaurant: Restaurant, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'add') {
      button.setAttribute('data-target', '#addRestaurantModal');
    }
    if(mode === 'edit') {
      this.editRestaurant = restaurant;
      button.setAttribute('data-target', '#updateRestaurantModal');
    }
    if(mode === 'delete') {
      this.deleteRestaurant = restaurant;
      button.setAttribute('data-target', '#deleteRestaurantModal');
    }
    container.appendChild(button);
    button.click();
  }

}
