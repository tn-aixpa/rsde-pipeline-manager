import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-draw';
@Component({
  selector: 'app-map-input',
  standalone: true,
  imports: [],
  templateUrl: './map-input.component.html',
  styleUrl: './map-input.component.scss'
})
export class MapInputComponent implements AfterViewInit{

  @Output() drawEvent = new EventEmitter();
  polyline: any;

  private map!: L.Map;

  editableLayers: L.FeatureGroup = new L.FeatureGroup();
  drawControl = new L.Control.Draw({
    position: 'topleft',
    draw: {
      polygon: {
        allowIntersection: false,
        drawError: {
          color: '#e1e100',
          message:
            'The sides of the polygon cannot intersect. Please draw a valid shape',
        },
        shapeOptions: {
          color: '#3880ff',
        },
      },
      rectangle: <any>{
        showArea: false,
        shapeOptions: {
          color: '#3880ff',
        },
      },
      polyline: false,
      circle: false,
      marker: false,
      circlemarker: false,
    },
  });

  editControl = new L.Control.Draw({
    position: 'topleft',
    draw: {
      polygon: false,
      rectangle: false,
      polyline: false,
      circle: false,
      marker: false,
      circlemarker: false,
    },
    edit: <any>{
      poly: {
        allowIntersection: false,
      },
      remove: true,
      featureGroup: this.editableLayers,
    },
  });

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
    // this.translateService.stream('mapInput').subscribe((mapInput: any) => {
    //   L.drawLocal.draw = mapInput.draw;
    //   L.drawLocal.edit = mapInput.edit;
    //   (this.drawControl.options as any).draw.polygon.drawError.message =
    //     mapInput.intersectionError;
    // });

    this.map.addLayer(this.editableLayers);
    this.map.addControl(this.drawControl);

    // L.EditToolbar.include({
    //   getActions: function (handler: any) {
    //     const actions = [];

    //     if (handler.removeAllLayers) {
    //       actions.push({
    //         title: L.drawLocal.edit.toolbar.actions.clearAll.title,
    //         text: L.drawLocal.edit.toolbar.actions.clearAll.text,
    //         callback: this._clearAllLayers,
    //         context: this,
    //       });
    //     }

    //     if (handler.type === 'edit') {
    //       actions.push({
    //         title: L.drawLocal.edit.toolbar.actions.save.title,
    //         text: L.drawLocal.edit.toolbar.actions.save.text,
    //         callback: this._save,
    //         context: this,
    //       });
    //     }

    //     actions.push({
    //       title: L.drawLocal.edit.toolbar.actions.cancel.title,
    //       text: L.drawLocal.edit.toolbar.actions.cancel.text,
    //       callback: this.disable,
    //       context: this,
    //     });

    //     return actions;
    //   },
    // });

    this.map.on('draw:created', (e: any) => {
      this.drawEvent.emit(e);

      this.drawControl.remove();
      this.editControl.addTo(this.map);

      this.editableLayers.addLayer(e.layer);

      document
        .querySelector('.leaflet-interactive')
        ?.classList.remove('leaflet-interactive');
    });

    this.map.on('draw:deleted', (e: any) => {
      this.drawEvent.emit(e);

      this.editControl.remove();
      this.drawControl.addTo(this.map);
    });
    // fixes the problem with map not showing the tiles correctly
    setTimeout(() => { this.map.invalidateSize()}, 400);
  }

}
