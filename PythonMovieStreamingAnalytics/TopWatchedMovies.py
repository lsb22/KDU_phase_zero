filename = ""
titles = []

while True:
    filename = input("Enter the CSV file name (or path) having movies list: ").strip()

    # check if it's a CSV
    if filename[-3:len(filename)] != "csv":
        print("Please provide CSV file name with correct path as an input")
        print()
    else:
        try:
            f = open(filename)
            titles = f.read().split(",")

            # check for empty file
            if len(titles) == 1 and len(titles[0].strip()) == 0: 
                print("Please provide a non-empty file")
                continue
            break
        except:
            print("Error while opening file, check if the file exists or path is correct")
            print()


titlesDict = dict()

for i in range(len(titles)):
    titles[i] = titles[i].strip()
    if len(titles[i]) == 0: continue
    titlesDict[titles[i]] = titlesDict.get(titles[i],0)+1

# sort dictionary
sorted_dict = sorted(titlesDict.items(), key = lambda curr : -curr[1])

print("Top 3 movies with the highest watch count: ")
print(sorted_dict[0][0],":",sorted_dict[0][1])
print(sorted_dict[1][0],":",sorted_dict[1][1])
print(sorted_dict[2][0],":",sorted_dict[2][1])