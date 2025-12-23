import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { useState } from "react";

const AddMovies = () => {
  const [ratings, setRatings] = useState("Select a rating ⭐");
  return (
    <div className="border w-full py-7 px-3 flex flex-col gap-y-7 rounded-md">
      <Input
        type="text"
        placeholder="Enter your movie name"
        className="h-10 text-center"
      />
      <div className="border w-full rounded-md">
        <DropdownMenu>
          <DropdownMenuTrigger className="w-full p-2">
            Rating: {ratings}
          </DropdownMenuTrigger>
          <DropdownMenuContent className="bg-black w-93 text-white">
            <DropdownMenuItem onClick={() => setRatings("⭐⭐⭐⭐⭐(5 Stars)")}>
              ⭐⭐⭐⭐⭐(5 Stars)
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => setRatings("⭐⭐⭐⭐(4 Stars)")}>
              ⭐⭐⭐⭐(4 Stars)
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => setRatings("⭐⭐⭐(3 Stars)")}>
              ⭐⭐⭐(3 Stars)
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => setRatings("⭐⭐(2 Stars)")}>
              ⭐⭐(2 Stars)
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => setRatings("⭐(1 Star)")}>
              ⭐(1 Star)
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
      <Button className="bg-blue-500 hover:bg-blue-600 p-4 active:scale-[0.9]">
        Add to Watchlist
      </Button>
    </div>
  );
};

export default AddMovies;
