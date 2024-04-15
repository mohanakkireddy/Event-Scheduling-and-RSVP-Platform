import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvesComponent } from './eves.component';

describe('EvesComponent', () => {
  let component: EvesComponent;
  let fixture: ComponentFixture<EvesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EvesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EvesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
