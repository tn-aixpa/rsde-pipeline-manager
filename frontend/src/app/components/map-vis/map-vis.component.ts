import { AfterViewInit, Component, Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map-vis',
  standalone: true,
  imports: [],
  templateUrl: './map-vis.component.html',
  styleUrl: './map-vis.component.scss'
})
export class MapVisComponent implements AfterViewInit{

  private map!: L.Map;
  
  @Input()
  polyline: string = '';

  ngAfterViewInit() {
    this.initMap();
  }

  initMap(): void {
    this.map = L.map('map', {
      center: L.latLng(46.07645610073175, 11.117546035606123),
      zoom: 9,
      layers: [
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          maxZoom: 18,
          attribution: 'Map data © OpenStreetMap contributors',
        }),
      ],
    });

    if (this.polyline) {
      let str = this.polyline.substring(this.polyline.indexOf('((') + 2, this.polyline.lastIndexOf('))'));
      console.log('Polyline:', str);
      const latLngs = str.split(',').map(coord => {
        const [lng, lat] = coord.trim().split(' ').map(Number);
        return L.latLng(lat, lng);
      });
      latLngs.push(latLngs[0]);

      const polyline = L.polyline(latLngs, { color: 'blue' }).addTo(this.map);
      this.map.fitBounds(polyline.getBounds());
    }

    setTimeout(() => { this.map.invalidateSize()}, 400);
  }  
}
