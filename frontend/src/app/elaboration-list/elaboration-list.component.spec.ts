import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElaborationListComponent } from './elaboration-list.component';

describe('ElaborationListComponent', () => {
  let component: ElaborationListComponent;
  let fixture: ComponentFixture<ElaborationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElaborationListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElaborationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
