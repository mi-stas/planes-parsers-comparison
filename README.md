# A comparison of planes masks parsers
In this comparison, data is extracted from the plane mask obtained after applying the SLAM recognition algorithm. 

Each plane is described by its own color so each pixel that belongs to a particular plane is determined. 

For each pixel that belongs to the plane, it's position is finding.

## Test data

In comparison, three **PNG** images with a resolution of **640x480** were parsed, ***planes_example_small.png*** contains **27%** of the plane pixels from the total number, ***planes_example_medium.png*** contains **52%**, ***planes_example_big.png*** contains **81%**.

These three **PNG** images were converted into **JPG** format and then they were also parsed.

## Configuration:

CPU: AMD Ryzen 7 3750H (2.3 GHz) 

RAM: 16GB (2x8GB) 2666MHz 

Storage: 256GB SSD, 1TB HDD 

OS: Windows 10 64-bit

## Results of 10000 measurements for each image

### Parsing of ***planes_example_small.png***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|6.68|1.59|
|2|Korim|9.50|2.72|
|3|Scrimage|25.49|17.09|
|4|OpenImaj|27.12|11.83|

### Parsing of ***planes_example_medium.png***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|8.89|1.30|
|2|Korim|13.78|2.68|
|3|Scrimage|27.10|14.48|
|4|OpenImaj|30.06|2.44|

### Parsing of ***planes_example_big.png***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|10.67|1.43|
|2|Korim|14.93|2.53|
|3|Scrimage|28.66|15.90|
|4|OpenImaj|32.28|3.48|

### Parsing of ***planes_example_small.jpg***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|8.78|1.50|
|2|Korim|12.72|5.40|
|3|Scrimage|24.48|15.61|
|4|OpenImaj|28.60|2.20|

### Parsing of ***planes_example_medium.jpg***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|11.23|1.78|
|2|Korim|15.49|3.96|
|3|Scrimage|26.68|14.07|
|4|OpenImaj|32.40|4.73|

### Parsing of ***planes_example_big.jpg***

|№|Library|Average time (millis)|Standard deviation|
|-|--------------|-----|-----|
|1|AWT (java built in)|13.14|2.52|
|2|Korim|16.70|3.27|
|3|Scrimage|29.03|15.80|
|4|OpenImaj|33.66|3.53|
