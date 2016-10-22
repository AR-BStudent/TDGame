This file will contain TODOs and ideas.

TODO: Make ideas
AM
*Collision detection
*Angular acceleration

ARB
*Scaling/window resizing response
*Listeners
*Scene Manager - move from model to modular system

-------------------------
*************************
MAP FILE FORMAT
*************************
-------------------------
Width,Height
#
#
#	The next block is the tile list. 0 means not part of path, while the rest of the path is indicated in order that each tile is in the path.
#
#
entryDirection,exitDirection

Where entry direction and exit direction is denoted by;
0 : UP
1 : RIGHT
2 : DOWN
3 : LEFT

WIP

Over-hall of rendering pipeline
Each renderable will contain render info (such as transform, Sprite etc.)
On creation, renderable will add itself automatically to render queue
on deletion? remove from queue