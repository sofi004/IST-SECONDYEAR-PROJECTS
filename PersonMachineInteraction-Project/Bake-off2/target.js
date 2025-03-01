// Target class (position and width)
class Target
{ 
  constructor(x, y, w, l, id, c)
  {
    this.x      = x;
    this.y      = y;
    this.width  = w;
    this.label  = l;
    this.id     = id;
    this.cor    = c;
  }
  
  // Checks if a mouse click took place
  // within the target
  clicked(mouse_x, mouse_y)
  {
    return dist(this.x, this.y, mouse_x, mouse_y) < this.width / 2;
  }
  
  // Draws the target (i.e., a circle)
  // and its label
  draw()
  {
    // Draw target
    fill(this.cor);                  
    circle(this.x, this.y, this.width);
    
    // Draw label
    textStyle(BOLD);
    textFont("Arial", 16);
    fill(color(255,255,255));
    textAlign(CENTER);
    text(this.label, this.x, this.y);
    textStyle(NORMAL);
  }
}