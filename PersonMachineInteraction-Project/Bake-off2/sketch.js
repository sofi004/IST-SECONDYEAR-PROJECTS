// Bake-off #2 -- Seleção em Interfaces Densas
// IPM 2023-24, Período 3
// Entrega: até às 23h59, dois dias úteis antes do sexto lab (via Fenix)
// Bake-off: durante os laboratórios da semana de 18 de Março

// p5.js reference: https://p5js.org/reference/

// Database (CHANGE THESE!)
const GROUP_NUMBER        = 0;      // Add your group number here as an integer (e.g., 2, 3)
const RECORD_TO_FIREBASE  = false;  // Set to 'true' to record user results to Firebase

// Pixel density and setup variables (DO NOT CHANGE!)
let PPI, PPCM;
const NUM_OF_TRIALS       = 12;     // The numbers of trials (i.e., target selections) to be completed
let continue_button;
let legendas;                       // The item list from the "legendas" CSV

// Metrics (DO NOT CHANGE!)
let testStartTime, testEndTime;     // time between the start and end of one attempt (8 trials)
let hits 			      = 0;      // number of successful selections
let misses 			      = 0;      // number of missed selections (used to calculate accuracy)
let database;                       // Firebase DB  

// Study control parameters (DO NOT CHANGE!)
let draw_targets          = false;  // used to control what to show in draw()
let trials;                         // contains the order of targets that activate in the test
let current_trial         = 0;      // the current trial number (indexes into trials array above)
let attempt               = 0;      // users complete each test twice to account for practice (attemps 0 and 1)

// Target list and layout variables
let targets               = [];
const GRID_ROWS           = 8;      // We divide our 80 targets in a 8x10 grid
const GRID_COLUMNS        = 10;     // We divide our 80 targets in a 8x10 grid

//Some stuff of mine
let paises                = [];
let botoes                = [];
let targets100            = [];
let etiquetas100          = [];
let targets101            = [];
let etiquetas101          = [];
let targets102            = [];
let etiquetas102          = [];
let targets103            = [];
let etiquetas103          = [];
let targets104            = [];
let etiquetas104          = [];
let targets105            = [];
let etiquetas105          = [];
let draw10                = [false, false, false, false, false, false];
let prevbotao             = 0; 



// Ensures important data is loaded before the program starts
function preload()
{
  // id,name,...
  legendas = loadTable('legendas.csv', 'csv', 'header');
  catching = loadSound('catching.mp3');
}

// Runs once at the start
function setup()
{
  vermelho = color(204, 0, 0)
  laranja = color(204, 100, 0)
  amarelo = color(153, 153, 0)
  lima = color(80, 180, 0)
  verde = color(0, 120, 0)
  jade = color(0, 160, 102)
  ciano = color(0, 204, 204)
  azul = color(0, 128, 255)
  naval = color(0, 0, 153)
  roxo = color(102, 0, 102)
  rosa = color(204, 0, 204)
  magenta = color(208, 0, 55)
  castanho = color(153, 76, 0)
  violeta = color(190, 114, 255)
  
  for (var i = 0; i < legendas.getRowCount(); i++){
    paises.push([legendas.getString(i,1), i + 1]);
  }
  paises.sort();
  print(paises);
  
  createCanvas(700, 500);    // window size in px before we go into fullScreen()
  frameRate(60);             // frame rate (DO NOT CHANGE!)
  
  randomizeTrials();         // randomize the trial order at the start of execution
  drawUserIDScreen();        // draws the user start-up screen (student ID and display size)
}

// Runs every frame and redraws the screen
function draw()
{
  if (draw_targets && attempt < 2)
  {     
    // The user is interacting with the 6x3 target grid
    background(color(0,0,0));        // sets background to purple
    
    // Print trial count at the top left-corner of the canvas
    textFont("Arial", 16);
    fill(color(255,255,255));
    textAlign(LEFT);
    text("Trial " + (current_trial + 1) + " of " + trials.length, 50, 20);
    
    // Hover botoes
    for (var i = 0; i < 6; i++){
      if (botoes[i].clicked(mouseX, mouseY)){
        print (botoes[i].id);
        if (botoes[i].id === 100){
          draw10[prevbotao] = false;
          draw10[0] = true;
          prevbotao = 0;
        }
        if (botoes[i].id === 101){
          draw10[prevbotao] = false;
          draw10[1] = true;
          prevbotao = 1;
        }
        if (botoes[i].id === 102){
          draw10[prevbotao] = false;
          draw10[2] = true;
          prevbotao = 2;
        }
        if (botoes[i].id === 103){
          draw10[prevbotao] = false;
          draw10[3] = true;
          prevbotao = 3;
        }
        if (botoes[i].id === 104){
          draw10[prevbotao] = false;
          draw10[4] = true;
          prevbotao = 4;
        }
        if (botoes[i].id === 105){
          draw10[prevbotao] = false;
          draw10[5] = true;
          prevbotao = 5;
        }
        break;
      } 
    }

    // Draw botoes
    for (var i = 0; i < 6; i++) botoes[i].draw();

    if (draw10[0]){
      for (var j = 0; j < 13; j++) etiquetas100[j].draw(); 
      for (var j = 0; j < 27; j++) targets100[j].draw();
    }

    if (draw10[1]){
      for (var j = 0; j < 6; j++) etiquetas101[j].draw();
      for (var j = 0; j < 11; j++) targets101[j].draw();
    }

    if (draw10[2]){
      for (var j = 0; j < 5; j++) etiquetas102[j].draw();
      for (var j = 0; j < 9; j++) targets102[j].draw();
    }

    if (draw10[3]){
      for (var j = 0; j < 5; j++) etiquetas103[j].draw();
      for (var j = 0; j < 13; j++) targets103[j].draw();
    }

    if (draw10[4]){
      for (var j = 0; j < 6; j++) etiquetas104[j].draw();
      for (var j = 0; j < 10; j++) targets104[j].draw();
    }

    if (draw10[5]){
      for (var j = 0; j < 5; j++) etiquetas105[j].draw();
      for (var j = 0; j < 10; j++) targets105[j].draw();
    }
        
    // Draw all targets
	  //for (var i = 0; i < legendas.getRowCount(); i++) targets[i].draw();
    
    // Draws the target label to be selected in the current trial. We include 
    // a black rectangle behind the trial label for optimal contrast in case 
    // you change the background colour of the sketch (DO NOT CHANGE THESE!)
    fill(color(0,0,0));
    rect(0, height - 40, width, 40);
 
    textFont("Arial", 20); 
    fill(color(255,255,255)); 
    textAlign(CENTER); 
    text(legendas.getString(trials[current_trial]-1,1), width/2, height - 20);
  }
}

// Print and save results at the end of 54 trials
function printAndSavePerformance()
{
  // DO NOT CHANGE THESE! 
  let accuracy			= parseFloat(hits * 100) / parseFloat(hits + misses);
  let test_time         = (testEndTime - testStartTime) / 1000;
  let time_per_target   = nf((test_time) / parseFloat(hits + misses), 0, 3);
  let penalty           = constrain((((parseFloat(95) - (parseFloat(hits * 100) / parseFloat(hits + misses))) * 0.2)), 0, 100);
  let target_w_penalty	= nf(((test_time) / parseFloat(hits + misses) + penalty), 0, 3);
  let timestamp         = day() + "/" + month() + "/" + year() + "  " + hour() + ":" + minute() + ":" + second();
  
  textFont("Arial", 18);
  background(color(0,0,0));   // clears screen
  fill(color(255,255,255));   // set text fill color to white
  textAlign(LEFT);
  text(timestamp, 10, 20);    // display time on screen (top-left corner)
  
  textAlign(CENTER);
  text("Attempt " + (attempt + 1) + " out of 2 completed!", width/2, 60); 
  text("Hits: " + hits, width/2, 100);
  text("Misses: " + misses, width/2, 120);
  text("Accuracy: " + accuracy + "%", width/2, 140);
  text("Total time taken: " + test_time + "s", width/2, 160);
  text("Average time per target: " + time_per_target + "s", width/2, 180);
  text("Average time for each target (+ penalty): " + target_w_penalty + "s", width/2, 220);

  // Saves results (DO NOT CHANGE!)
  let attempt_data = 
  {
        project_from:       GROUP_NUMBER,
        assessed_by:        student_ID,
        test_completed_by:  timestamp,
        attempt:            attempt,
        hits:               hits,
        misses:             misses,
        accuracy:           accuracy,
        attempt_duration:   test_time,
        time_per_target:    time_per_target,
        target_w_penalty:   target_w_penalty,
  }
  
  // Sends data to DB (DO NOT CHANGE!)
  if (RECORD_TO_FIREBASE)
  {
    // Access the Firebase DB
    if (attempt === 0)
    {
      firebase.initializeApp(firebaseConfig);
      database = firebase.database();
    }
    
    // Adds user performance results
    let db_ref = database.ref('G' + GROUP_NUMBER);
    db_ref.push(attempt_data);
  }
}

// Mouse button was pressed - lets test to see if hit was in the correct target
function mousePressed() 
{
  // Only look for mouse releases during the actual test
  // (i.e., during target selections)
  if (draw_targets)
  {
    if (draw10[0]){
      for (var i = 0; i < 27; i++){
        if (targets100[i].clicked(mouseX, mouseY)){
          print (targets100[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets100[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    if (draw10[1]){
      for (var i = 0; i < 11; i++){
        if (targets101[i].clicked(mouseX, mouseY)){
          print (targets101[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets101[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    if (draw10[2]){
      for (var i = 0; i < 9; i++){
        if (targets102[i].clicked(mouseX, mouseY)){
          print (targets102[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets102[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    if (draw10[3]){
      for (var i = 0; i < 13; i++){
        if (targets103[i].clicked(mouseX, mouseY)){
          print (targets103[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets103[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    if (draw10[4]){
      for (var i = 0; i < 10; i++){
        if (targets104[i].clicked(mouseX, mouseY)){
          print (targets104[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets104[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    if (draw10[5]){
      for (var i = 0; i < 10; i++){
        if (targets105[i].clicked(mouseX, mouseY)){
          print (targets105[i].id);
          print (trials[current_trial]);
          // Checks if it was the correct target
          catching.play();
          if (targets105[i].id === trials[current_trial]) hits++;
          else misses++;
          current_trial++;              // Move on to the next trial/target
          break;
        }
      }
    }

    /*for (var i = 0; i < legendas.getRowCount(); i++)
    {
      // Check if the user clicked over one of the targets
      if (targets[i].clicked(mouseX, mouseY)) 
      {
        print (targets[i].id);
        print (trials[current_trial])
        // Checks if it was the correct target
        if (targets[i].id === trials[current_trial]) hits++;
        else misses++;
        
        current_trial++;              // Move on to the next trial/target
        break;
      }
    }*/
    
    // Check if the user has completed all trials
    if (current_trial === NUM_OF_TRIALS)
    {
      testEndTime = millis();
      draw_targets = false;          // Stop showing targets and the user performance results
      printAndSavePerformance();     // Print the user's results on-screen and send these to the DB
      attempt++;                      
      
      // If there's an attempt to go create a button to start this
      if (attempt < 2)
      {
        continue_button = createButton('START 2ND ATTEMPT');
        continue_button.mouseReleased(continueTest);
        continue_button.position(width/2 - continue_button.size().width/2, height/2 - continue_button.size().height/2);
      }
    }
    // Check if this was the first selection in an attempt
    else if (current_trial === 1) testStartTime = millis(); 
  }
}

// Evoked after the user starts its second (and last) attempt
function continueTest()
{
  // Re-randomize the trial order
  randomizeTrials();
  
  // Resets performance variables
  hits = 0;
  misses = 0;
  
  current_trial = 0;
  continue_button.remove();
  
  // Shows the targets again
  draw_targets = true; 
}

function createBotoes(target_size, horizontal_gap, vertical_gap)
{
  // Define the margins between targets by dividing the white space 
  // for the number of targets minus one
  h_margin = horizontal_gap / (GRID_COLUMNS -1);
  v_margin = vertical_gap / (GRID_ROWS - 1);
  
  // Set targets in a 8 x 10 grid
  for (var r = 0; r < GRID_ROWS; r++)
  {
    for (var c = 0; c < GRID_COLUMNS; c++)
    {
      let target_x = 40 + (h_margin + target_size) * c + target_size/2;        // give it some margin from the left border
      let target_y = (v_margin + target_size) * r + target_size/2;
      
      // Find the appropriate label and ID for this target
      let legendas_index = c + GRID_COLUMNS * r;
      let target_id = paises[legendas_index][1];  
      let target_label = paises[legendas_index][0]; 
      
      let botao = new Botao(target_x, target_y + 40, target_size, target_label, target_id);
      botoes.push(botao);
    }  
  }
}

// Creates and positions the UI targets
function createTargets(target_size, horizontal_gap, vertical_gap)
{
  // Define the margins between targets by dividing the white space 
  // for the number of targets minus one
  h_margin = horizontal_gap / (GRID_COLUMNS -1);
  v_margin = vertical_gap / (GRID_ROWS - 1);
  
  // Set targets in a 8 x 10 grid
  for (var r = 0; r < GRID_ROWS; r++)
  {
    for (var c = 0; c < GRID_COLUMNS; c++)
    {
      let target_x = 40 + (h_margin + target_size) * c + target_size/2;        // give it some margin from the left border
      let target_y = (v_margin + target_size) * r + target_size/2;
      
      // Find the appropriate label and ID for this target
      let legendas_index = c + GRID_COLUMNS * r;
      let target_id = paises[legendas_index][1];  
      let target_label = paises[legendas_index][0]; 
      
      let target = new Target(target_x, target_y + 40, target_size, target_label, target_id);
      targets.push(target);
    }  
  }
}

// Is invoked when the canvas is resized (e.g., when we go fullscreen)
function windowResized() 
{
  if (fullscreen())
  {
    resizeCanvas(windowWidth, windowHeight);
    
    // DO NOT CHANGE THE NEXT THREE LINES!
    let display        = new Display({ diagonal: display_size }, window.screen);
    PPI                = display.ppi;                      // calculates pixels per inch
    PPCM               = PPI / 2.54;                       // calculates pixels per cm
  
    // Make your decisions in 'cm', so that targets have the same size for all participants
    // Below we find out out white space we can have between 2 cm targets
    let screen_width   = display.width * 2.54;             // screen width
    let screen_height  = display.height * 2.54;            // screen height
    let target_size    = 2;                                // sets the target size (will be converted to cm when passed to createTargets)
    let horizontal_gap = screen_width - target_size * GRID_COLUMNS;// empty space in cm across the x-axis (based on 10 targets per row)
    let vertical_gap   = screen_height - target_size * GRID_ROWS;  // empty space in cm across the y-axis (based on 8 targets per column)

    // Creates and positions the UI targets according to the white space defined above (in cm!)
    // 80 represent some margins around the display (e.g., for text)
    //createTargets(target_size * PPCM, horizontal_gap * PPCM - 80, vertical_gap * PPCM - 80);

    //[botao_x, botao_y, botao_size, botao_label, botao_id]
    botoes.push(new Botao(screen_width * 300 / 700 * PPCM, screen_height * 215 / 500 * PPCM, target_size * PPCM, 'Ba', 100));
    botoes.push(new Botao(screen_width * 350 / 700 * PPCM, screen_height * 180 / 500 * PPCM, target_size * PPCM, 'Be', 101));
    botoes.push(new Botao(screen_width * 400 / 700 * PPCM, screen_height * 215 / 500 * PPCM, target_size * PPCM, 'Bi', 102));
    botoes.push(new Botao(screen_width * 300 / 700 * PPCM, screen_height * 285 / 500 * PPCM, target_size * PPCM, 'Br', 103));
    botoes.push(new Botao(screen_width * 350 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Bu', 104));
    botoes.push(new Botao(screen_width * 400 / 700 * PPCM, screen_height * 285 / 500 * PPCM, target_size * PPCM, 'Bh Bl Bn\nBo By', 105));

    //targets100
    etiquetas100.push(new Etiqueta(screen_width * 25 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Bac', vermelho));
    targets100.push(new Target(screen_width * 75 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Bacolod', 36, vermelho));
    
    etiquetas100.push(new Etiqueta(screen_width * 125 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Bad', laranja));
    targets100.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Badajoz', 56, laranja));
    
    etiquetas100.push(new Etiqueta(screen_width * 250 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Baf', amarelo));
    targets100.push(new Target(screen_width * 300 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Bafoussam', 74, amarelo));

    
    etiquetas100.push(new Etiqueta(screen_width * 10 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Bag', lima));
    targets100.push(new Target(screen_width * 50 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Bagé', 66, lima));
    targets100.push(new Target(screen_width * 100 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Baghdad', 68, lima));
    targets100.push(new Target(screen_width * 150 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Bago', 75, lima));
    targets100.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Baguio', 42, lima));

    etiquetas100.push(new Etiqueta(screen_width * 235 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Bah', verde));
    targets100.push(new Target(screen_width * 275 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Bahawalpur', 41, verde));
    targets100.push(new Target(screen_width * 325 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Bahía Blanca', 49, verde));

    
    etiquetas100.push(new Etiqueta(screen_width * 75 / 700 * PPCM, screen_height * 160 / 500 * PPCM, 'Bak', jade));
    targets100.push(new Target(screen_width * 125 / 700 * PPCM, screen_height * 160 / 500 * PPCM, target_size * PPCM, 'Baku', 14, jade));
    
    etiquetas100.push(new Etiqueta(screen_width * 175 / 700 * PPCM, screen_height * 160 / 500 * PPCM, 'Bal', ciano));
    targets100.push(new Target(screen_width * 225 / 700 * PPCM, screen_height * 160 / 500 * PPCM, target_size * PPCM, 'Balikpapan', 46, ciano));

    
    etiquetas100.push(new Etiqueta(screen_width * 50/ 700 * PPCM, screen_height * 220 / 500 * PPCM, 'Bam', azul));
    targets100.push(new Target(screen_width * 100 / 700 * PPCM, screen_height * 220 / 500 * PPCM, target_size * PPCM, 'Bamako', 33, azul));

    etiquetas100.push(new Etiqueta(screen_width * 150 / 700 * PPCM, screen_height * 220 / 500 * PPCM, 'Ban', naval));
    targets100.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 220 / 500 * PPCM, target_size * PPCM, 'Bandung', 19, naval));
    targets100.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 220 / 500 * PPCM, target_size * PPCM, 'Banjul', 26, naval));

    
    etiquetas100.push(new Etiqueta(screen_width * 75 / 700 * PPCM, screen_height * 280 / 500 * PPCM, 'Bao', roxo));
    targets100.push(new Target(screen_width * 125 / 700 * PPCM, screen_height * 280 / 500 * PPCM, target_size * PPCM, 'Baoding', 80, roxo));
    targets100.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 280 / 500 * PPCM, target_size * PPCM, 'Baotou', 37, roxo));


    etiquetas100.push(new Etiqueta(screen_width * 10 / 700 * PPCM, screen_height * 340 / 500 * PPCM, 'Bar', rosa));
    targets100.push(new Target(screen_width * 50 / 700 * PPCM, screen_height * 340 / 500 * PPCM, target_size * PPCM, 'Barakaldo', 72, rosa));
    targets100.push(new Target(screen_width * 100 / 700 * PPCM, screen_height * 340 / 500 * PPCM, target_size * PPCM, 'Baranagar', 65, rosa));
    targets100.push(new Target(screen_width * 150 / 700 * PPCM, screen_height * 340 / 500 * PPCM, target_size * PPCM, 'Barcelona', 1, rosa));

    targets100.push(new Target(screen_width * 25 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Bari', 29, rosa));
    targets100.push(new Target(screen_width * 75 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Baroda', 54, rosa));
    targets100.push(new Target(screen_width * 125 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Barquisimeto', 34, rosa));
    targets100.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Barranquilla', 28, rosa));

    etiquetas100.push(new Etiqueta(screen_width * 185 / 700 * PPCM, screen_height * 340 / 500 * PPCM, 'Bat', magenta));
    targets100.push(new Target(screen_width * 225 / 700 * PPCM, screen_height * 340 / 500 * PPCM, target_size * PPCM, 'Baton Rouge', 21, magenta));
    targets100.push(new Target(screen_width * 275 / 700 * PPCM, screen_height * 340 / 500 * PPCM, target_size * PPCM, 'Batumi', 52, magenta));
    
    etiquetas100.push(new Etiqueta(screen_width * 210 / 700 * PPCM, screen_height * 400 / 500 * PPCM, 'Bay', castanho));
    targets100.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Bayamo', 58, castanho));
    targets100.push(new Target(screen_width * 300 / 700 * PPCM, screen_height * 400 / 500 * PPCM, target_size * PPCM, 'Bayreuth', 79, castanho));

    //targets101
    etiquetas101.push(new Etiqueta(screen_width * 125 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Bec', vermelho));
    targets101.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Béchar', 63, vermelho));
    
    etiquetas101.push(new Etiqueta(screen_width * 225 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Bei', verde));
    targets101.push(new Target(screen_width * 275 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Beijing', 2, verde));
    targets101.push(new Target(screen_width * 325 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Beira', 43, verde));
    targets101.push(new Target(screen_width * 375 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Beirut', 11, verde));
    
    etiquetas101.push(new Etiqueta(screen_width * 425 / 700 * PPCM, screen_height * 40 / 500 * PPCM, 'Bel', ciano));
    targets101.push(new Target(screen_width * 475 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Belgrade', 12, ciano));
    targets101.push(new Target(screen_width * 525 / 700 * PPCM, screen_height * 40 / 500 * PPCM, target_size * PPCM, 'Belém', 67, ciano));

    etiquetas101.push(new Etiqueta(screen_width * 150 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Ben', naval));
    targets101.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Benevento', 40, naval));
    targets101.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Benešov', 57, naval));
    targets101.push(new Target(screen_width * 300 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Benghazi', 30, naval));
    
    etiquetas101.push(new Etiqueta(screen_width * 350 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Bep', roxo));
    targets101.push(new Target(screen_width * 400 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Beppu', 77, roxo));
    
    etiquetas101.push(new Etiqueta(screen_width * 440 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Ber', rosa));
    targets101.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Berlin', 3, rosa));

    //targets102
    etiquetas102.push(new Etiqueta(screen_width * 400 / 700 * PPCM, screen_height * 100 / 500 * PPCM, 'Bia', vermelho));
    targets102.push(new Target(screen_width * 450 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Bial Podlaska', 71, vermelho));
    targets102.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Białogard', 73, vermelho));
    targets102.push(new Target(screen_width * 550 / 700 * PPCM, screen_height * 100 / 500 * PPCM, target_size * PPCM, 'Białystok', 48, vermelho));
    
    etiquetas102.push(new Etiqueta(screen_width * 425 / 700 * PPCM, screen_height * 160 / 500 * PPCM, 'Bik', jade));
    targets102.push(new Target(screen_width * 475 / 700 * PPCM, screen_height * 160 / 500 * PPCM, target_size * PPCM, 'Bikaner', 70, jade));
    
    etiquetas102.push(new Etiqueta(screen_width * 525 / 700 * PPCM, screen_height * 160 / 500 * PPCM, 'Bil', ciano));
    targets102.push(new Target(screen_width * 575 / 700 * PPCM, screen_height * 160 / 500 * PPCM, target_size * PPCM, 'Bilaspur', 62, ciano));
    targets102.push(new Target(screen_width * 625 / 700 * PPCM, screen_height * 160 / 500 * PPCM, target_size * PPCM, 'Bilbao', 22, ciano));
    
    etiquetas102.push(new Etiqueta(screen_width * 450 / 700 * PPCM, screen_height * 220 / 500 * PPCM, 'Bir', rosa));
    targets102.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 220 / 500 * PPCM, target_size * PPCM, 'Birendranagar', 78, rosa));
    targets102.push(new Target(screen_width * 550 / 700 * PPCM, screen_height * 220 / 500 * PPCM, target_size * PPCM, 'Birmingham', 13, rosa));

    etiquetas102.push(new Etiqueta(screen_width * 475 / 700 * PPCM, screen_height * 280 / 500 * PPCM, 'Bis', violeta));
    targets102.push(new Target(screen_width * 525 / 700 * PPCM, screen_height * 280 / 500 * PPCM, target_size * PPCM, 'Bishkek', 32, violeta));

    //targets103
    etiquetas103.push(new Etiqueta(screen_width * 10 / 700 * PPCM, screen_height * 260 / 500 * PPCM, 'Bra', vermelho));
    targets103.push(new Target(screen_width *  50 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Bradford', 31, vermelho));
    targets103.push(new Target(screen_width * 100 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Braga', 44, vermelho));
    targets103.push(new Target(screen_width * 150 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Bratislava', 16, vermelho));
    targets103.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Braunschweig', 64, vermelho));
    targets103.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Brașov', 61, vermelho));
    
    etiquetas103.push(new Etiqueta(screen_width * 75 / 700 * PPCM, screen_height * 320 / 500 * PPCM, 'Bre', amarelo));
    targets103.push(new Target(screen_width * 125 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Breda', 76, amarelo));
    targets103.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Bremen', 20, amarelo));
    targets103.push(new Target(screen_width * 225 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Brest', 51, amarelo));
    
    etiquetas103.push(new Etiqueta(screen_width * 100 / 700 * PPCM, screen_height * 380 / 500 * PPCM, 'Bri', verde));
    targets103.push(new Target(screen_width * 150 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Brindisi', 60, verde));
    targets103.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Brisbane', 6, verde));
    targets103.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Bristol', 17, verde));
    
    etiquetas103.push(new Etiqueta(screen_width * 125 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'Brn', naval));
    targets103.push(new Target(screen_width * 175 / 700 * PPCM, screen_height * 440/ 500 * PPCM, target_size * PPCM, 'Brno', 27, naval));
    
    etiquetas103.push(new Etiqueta(screen_width * 225 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'Bru', castanho));
    targets103.push(new Target(screen_width * 275 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Brussels', 7, castanho));

    //targets104
    etiquetas104.push(new Etiqueta(screen_width * 150 / 700 * PPCM, screen_height * 370 / 500 * PPCM, 'Buc', vermelho));
    targets104.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 370 / 500 * PPCM, target_size * PPCM, 'Bucaramanga', 35, vermelho));
    targets104.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 370 / 500 * PPCM, target_size * PPCM, 'Bucharest', 10, vermelho));
    targets104.push(new Target(screen_width * 300 / 700 * PPCM, screen_height * 370 / 500 * PPCM, target_size * PPCM, 'București', 39, vermelho));
    etiquetas104.push(new Etiqueta(screen_width * 350 / 700 * PPCM, screen_height * 370 / 500 * PPCM, 'Bud', laranja));
    targets104.push(new Target(screen_width * 400 / 700 * PPCM, screen_height * 370 / 500 * PPCM, target_size * PPCM, 'Budapest', 8, laranja));
    etiquetas104.push(new Etiqueta(screen_width * 450 / 700 * PPCM, screen_height * 370 / 500 * PPCM, 'Bue', amarelo));
    targets104.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 370 / 500 * PPCM, target_size * PPCM, 'Bueno Aires', 9, amarelo));

    etiquetas104.push(new Etiqueta(screen_width * 150 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'Bur', rosa));
    targets104.push(new Target(screen_width * 200 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Burbank', 59, rosa));
    targets104.push(new Target(screen_width * 250 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Burnaby', 45, rosa));
    targets104.push(new Target(screen_width * 300 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Bursa', 23, rosa));
    etiquetas104.push(new Etiqueta(screen_width * 350 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'Bus', violeta));
    targets104.push(new Target(screen_width * 400 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Busan', 18, violeta));
    etiquetas104.push(new Etiqueta(screen_width * 450 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'But', magenta));
    targets104.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Butuan', 47, magenta));

    //targets105
    etiquetas105.push(new Etiqueta(screen_width * 450 / 700 * PPCM, screen_height * 260 / 500 * PPCM, 'Bh', verde));
    targets105.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Bhilai', 38, verde));
    targets105.push(new Target(screen_width * 550 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Bhopal', 25, verde));
    targets105.push(new Target(screen_width * 600 / 700 * PPCM, screen_height * 260 / 500 * PPCM, target_size * PPCM, 'Bhubaneswar', 53, verde));
    
    etiquetas105.push(new Etiqueta(screen_width * 425 / 700 * PPCM, screen_height * 320 / 500 * PPCM, 'Bl', ciano));
    targets105.push(new Target(screen_width * 475 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Bloemfontein', 55, ciano));
    
    etiquetas105.push(new Etiqueta(screen_width * 525 / 700 * PPCM, screen_height * 320 / 500 * PPCM, 'Bn', naval));
    targets105.push(new Target(screen_width * 575 / 700 * PPCM, screen_height * 320 / 500 * PPCM, target_size * PPCM, 'Bne Brak', 69, naval));

    etiquetas105.push(new Etiqueta(screen_width * 400 / 700 * PPCM, screen_height * 380 / 500 * PPCM, 'Bo', roxo));
    targets105.push(new Target(screen_width * 450 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Bochum', 50, roxo));
    targets105.push(new Target(screen_width * 500 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Bogotá', 4, roxo));
    targets105.push(new Target(screen_width * 550 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Bologna', 15, roxo));
    targets105.push(new Target(screen_width * 600 / 700 * PPCM, screen_height * 380 / 500 * PPCM, target_size * PPCM, 'Boston', 5, roxo));
    
    etiquetas105.push(new Etiqueta(screen_width * 475 / 700 * PPCM, screen_height * 440 / 500 * PPCM, 'By', castanho));
    targets105.push(new Target(screen_width * 525 / 700 * PPCM, screen_height * 440 / 500 * PPCM, target_size * PPCM, 'Bydgoszcz', 24, castanho));


    // Starts drawing targets immediately after we go fullscreen
    draw_targets = true;
  }
}