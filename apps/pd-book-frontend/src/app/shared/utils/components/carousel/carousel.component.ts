import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carousel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss'],
})
export class CarouselComponent implements OnInit {
  currentIndex = 0;
  items: Array<{ title: string; description: string; image: string; buttonText: string }> = [];

  ngOnInit(): void {
    this.items = [
      {
        title: 'Card 1',
        description: 'Description for Card 1',
        image: 'path/to/image1.jpg',
        buttonText: 'Ver más',
      },
      {
        title: 'Card 2',
        description: 'Description for Card 2',
        image: 'path/to/image2.jpg',
        buttonText: 'Ver más',
      },
      {
        title: 'Card 3',
        description: 'Description for Card 3',
        image: 'path/to/image3.jpg',
        buttonText: 'Ver más',
      },
    ];
  }

  next() {
    this.currentIndex = (this.currentIndex + 1) % this.items.length;
  }

  prev() {
    this.currentIndex = (this.currentIndex - 1 + this.items.length) % this.items.length;
  }

  goTo(index: number) {
    this.currentIndex = index;
  }

  trackByIndex(index: number): number {
    return index;
  }
}
