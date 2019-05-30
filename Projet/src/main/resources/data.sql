INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (1, '/media/tiles/FOR_color.png', 'Foret', 'for', 1) on duplicate key update terrain_type =1;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (2, '/media/tiles/RIV_color.png', 'Riviere', 'riv', 2) on duplicate key update terrain_type =2;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (3, '/media/tiles/COM_color.png', 'Commerce', 'com', 3) on duplicate key update terrain_type =3;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (4, '/media/tiles/ECL_color.png', 'Ecole', 'ecl', 4) on duplicate key update terrain_type =4;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (5, '/media/tiles/EGL_color.png', 'Eglise', 'egl', 5) on duplicate key update terrain_type =5;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (6, '/media/tiles/POL_color.png', 'Police', 'pol', 6) on duplicate key update terrain_type =6;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (7, '/media/tiles/RES_color.png', 'Residence', 'res', 7) on duplicate key update terrain_type =7;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (8, '/media/tiles/RTB_color.png', 'Bus', 'rtb', 8) on duplicate key update terrain_type =8;
INSERT INTO terrain (id, terrain_img_url, terrain_nom, terrain_nom_short, terrain_type) VALUES (9, '/media/tiles/RTE_color.png', 'Route', 'rte', 9) on duplicate key update terrain_type =9;

