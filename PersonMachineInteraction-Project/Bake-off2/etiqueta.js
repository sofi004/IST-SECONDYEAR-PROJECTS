// Etiqueta class (position and width)
class Etiqueta
{
  constructor(x, y, l, c)
  {
    this.x      = x;
    this.y      = y;
    this.label  = l;
    this.colour = c;
  }
  
  draw()
  {
    // Draw label
    textFont("Arial", 24);
    fill(this.colour);
    textAlign(CENTER);
    text(this.label, this.x, this.y);
  }
}