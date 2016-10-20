This file will contain TODOs and ideas.

TODO: Make ideas
AM
*Unit arrive, flee, follow path, turn towards heading
*Path queue

ARB
*Priority queue based on z buffer
*renderable comparison via z buffer
*rendering optimisation

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