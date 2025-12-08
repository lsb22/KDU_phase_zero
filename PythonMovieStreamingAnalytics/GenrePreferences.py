genres = []

while True:
    genres = input("Enter 10 Genres in a single line each seperated by comma: ").split(",")

    # check if genres has 10 items
    if len(genres) != 10:
        print("Please enter exactly 10 generes, each seperated by comma")
        print()
    else: break

uniqueGenres = set()
genresDict = dict()

for i in range(10):
    genres[i] = genres[i].strip()
    if len(genres[i]) == 0:
        raise Exception("All values must be non empty")
    uniqueGenres.add(genres[i])
    genresDict[genres[i]] = genresDict.get(genres[i],0) + 1

print("List:",genres)
print("Set:",uniqueGenres)
print("Dictionary:",genresDict)